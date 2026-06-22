// 加载评论
async function loadComment(actId){
    let res = await axios.get(`${BASE_URL}/comment/list/${actId}`);
    let listBox = document.getElementById("commentList");
    listBox.innerHTML = "";
    res.data.data.forEach(c=>{
        listBox.innerHTML += `
            <div class="comment-item">
                <p>${c.content}</p>
                <small>${c.createTime}</small>
                ${userRole !== "STUDENT" ? `<button class="btn btn-sm btn-danger" onclick="delComment(${c.id})">删除</button>` : ""}
            </div>
        `
    })
}

// 提交评论
async function submitComment(){
    let content = document.getElementById("commentInput").value.trim();
    if(!content) return alert("评论不能为空");
    let data = {
        activityId: currentActId,
        content: content,
        parentId:0
    }
    await axios.post(`${BASE_URL}/comment/add`,data);
    document.getElementById("commentInput").value = "";
    loadComment(currentActId);
}

// 删除评论
async function delComment(commentId){
    await axios.post(`${BASE_URL}/comment/delete/${commentId}`);
    loadComment(currentActId);
}