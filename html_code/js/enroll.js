// 加载我的报名
async function loadMyEnroll(){
    let res = await axios.get(`${BASE_URL}/enroll/my`);
    let box = document.getElementById("myEnrollBox");
    box.innerHTML = "";
    if(res.data.data.length === 0){
        box.innerHTML = "<div class='alert alert-info'>暂无报名活动</div>";
        return;
    }
    res.data.data.forEach(item=>{
        let html = `
            <div class="card mb-2">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <span>活动ID：${item.activityId}</span>
                    <button class="btn btn-sm btn-danger" onclick="cancelEnroll(${item.activityId})">取消报名</button>
                </div>
            </div>
        `;
        box.innerHTML += html;
    })
}

// 取消报名
async function cancelEnroll(actId){
    let res = await axios.post(`${BASE_URL}/enroll/cancel/${actId}`);
    alert(res.data.msg);
    loadMyEnroll();
}

// 导出报名名单
function exportEnroll(actId){
    window.open(`${BASE_URL}/enroll/export/${actId}`)
}