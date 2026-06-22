// 加载活动回顾
async function loadReview(actId){
    let res = await axios.get(`${BASE_URL}/review/${actId}`);
    let review = res.data.data;
    let box = document.getElementById("reviewBox");
    if(!review){
        box.innerHTML = "<p>暂无活动回顾</p>";
        if(userRole !== "STUDENT"){
            box.innerHTML += `<button class="btn btn-primary" onclick="saveReview(${actId})">上传活动回顾</button>`;
        }
        return;
    }
    box.innerHTML = `
        <h6>活动总结回顾</h6>
        <p>${review.summary}</p>
        <p>点赞数：${review.likeNum}</p>
        <button class="btn btn-sm btn-success" onclick="likeAct(${actId})">点赞</button>
    `;
}

// 点赞
async function likeAct(actId){
    await axios.post(`${BASE_URL}/like/add/${actId}`);
    loadReview(actId);
}

// 保存活动回顾
async function saveReview(actId){
    let summary = prompt("输入活动总结：");
    if(!summary) return;
    let data = {activityId:actId,summary:summary,photos:""};
    await axios.post(`${BASE_URL}/review/add`,data);
    loadReview(actId);
}