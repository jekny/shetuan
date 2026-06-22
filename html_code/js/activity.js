// 加载活动列表
async function loadActivityList(){
    let status = document.getElementById("filterStatus").value;
    let params = {};
    if(status) params.status = status;
    let res = await axios.get(`${BASE_URL}/activity/list`,{params});
    let box = document.getElementById("activityCardBox");
    box.innerHTML = "";
    res.data.data.forEach(act=>{
        let card = `
            <div class="col-4 mb-3">
                <div class="card activity-card">
                    <div class="card-body">
                        <h5 class="card-title">${act.title}</h5>
                        <p class="card-text">地点：${act.location}</p>
                        <p class="card-text">时间：${act.startTime}</p>
                        <span class="badge ${act.status==='PUBLISH'?'bg-success':'bg-secondary'}">${act.status==='PUBLISH'?'进行中':'已取消'}</span>
                        <button class="btn btn-sm btn-primary mt-2" onclick="openActDetail(${act.id})">查看详情</button>
                        ${userRole === "STUDENT" ? `<button class="btn btn-sm btn-info mt-2" onclick="openScanModal(${act.id})">扫码签到</button>` : ""}
                    </div>
                </div>
            </div>
        `;
        box.innerHTML += card;
    })
}

// 打开签到弹窗
async function openScanModal(actId){
    currentActId = actId;
    await loadModalHtml("scanSignModal");
    new bootstrap.Modal(document.getElementById("scanSignModal")).show();
}

// 扫码签到执行
async function doScanSign(){
    let qr = document.getElementById("scanQrStr").value.trim();
    if(!qr) return alert("输入签到码");
    let res = await axios.post(`${BASE_URL}/sign/scan?qrCode=${qr}`);
    alert(res.data.msg);
    bootstrap.Modal.getInstance(document.getElementById("scanSignModal")).hide();
}

// 打开活动详情弹窗
async function openActDetail(actId){
    currentActId = actId;
    await loadModalHtml("actDetailModal");
    let res = await axios.get(`${BASE_URL}/activity/info/${actId}`);
    let act = res.data.data;
    document.getElementById("modalActTitle").innerText = act.title;
    let infoHtml = `
        <p>活动ID：${act.id}</p>
        <p>地点：${act.location}</p>
        <p>开始：${act.startTime}</p>
        <p>结束：${act.endTime}</p>
        <p>报名截止：${act.enrollEndTime}</p>
        <p>人数上限：${act.maxNum}</p>
        <p>详情：${act.content}</p>
    `;
    document.getElementById("actDetailInfo").innerHTML = infoHtml;
    if(userRole !== "STUDENT"){
        document.getElementById("qrBox").classList.remove("d-none");
        generateSignQr();
    }else{
        document.getElementById("qrBox").classList.add("d-none");
    }
    loadComment(actId);
    loadReview(actId);
    let btnBox = document.getElementById("modalBtnBox");
    if(userRole === "STUDENT"){
        btnBox.innerHTML = `<button class="btn btn-success" onclick="enrollAct(${actId})">立即报名</button>`;
    }else{
        btnBox.innerHTML = `
            <button class="btn btn-warning" onclick="cancelAct(${actId})">取消活动</button>
            <button class="btn btn-info" onclick="exportEnroll(${actId})">导出报名名单</button>
        `;
    }
    new bootstrap.Modal(document.getElementById("actDetailModal")).show();
}

// 生成签到二维码
async function generateSignQr(){
    let res = await axios.get(`${BASE_URL}/sign/create/${currentActId}`);
    let qrStr = res.data.data;
    let canvas = document.getElementById("qrCanvas");
    canvas.innerHTML = "";
    QRCode.toCanvas(canvas, qrStr, {width:200})
}

// 报名活动
async function enrollAct(actId){
    let res = await axios.post(`${BASE_URL}/enroll/add/${actId}`);
    alert(res.data.msg);
    loadActivityList();
}

// 取消活动（负责人）
async function cancelAct(actId){
    if(!confirm("确定取消该活动？")) return;
    let res = await axios.post(`${BASE_URL}/activity/cancel/${actId}`);
    alert(res.data.msg);
    bootstrap.Modal.getInstance(document.getElementById("actDetailModal")).hide();
    loadActivityList();
}

// 发布活动提交
async function publishActivity(){
    let data = {
        title: document.getElementById("actTitle").value,
        location: document.getElementById("actLoc").value,
        maxNum: Number(document.getElementById("actMax").value),
        startTime: document.getElementById("actStart").value.replace("T"," ")+":00",
        endTime: document.getElementById("actEnd").value.replace("T"," ")+":00",
        enrollEndTime: document.getElementById("actEnrollEnd").value.replace("T"," ")+":00",
        content: document.getElementById("actContent").value,
        clubId: 1
    }
    let res = await axios.post(`${BASE_URL}/activity/add`,data);
    alert(res.data.msg);
    // 切回活动列表
    document.querySelector('[data-page="activityList"]').click();
}