// 发布活动提交函数
async function publishActivity() {
    // 获取表单输入
    const actTitle = document.getElementById('actTitle').value.trim();
    const actStart = document.getElementById('actStart').value;
    const actEnd = document.getElementById('actEnd').value;
    const actLoc = document.getElementById('actLoc').value.trim();
    const actMax = document.getElementById('actMax').value;
    const actEnrollEnd = document.getElementById('actEnrollEnd').value;
    const actContent = document.getElementById('actContent').value.trim();

    // 前端校验
    if (!actTitle) {
        alert("请输入活动标题");
        return;
    }
    if (!actStart || !actEnd) {
        alert("请选择活动起止时间");
        return;
    }
    if (new Date(actStart) >= new Date(actEnd)) {
        alert("活动结束时间必须晚于开始时间");
        return;
    }
    if (!actLoc) {
        alert("请填写活动地点");
        return;
    }
    if (!actMax || Number(actMax) <= 0) {
        alert("报名人数上限必须大于0");
        return;
    }
    if (!actEnrollEnd) {
        alert("请选择报名截止时间");
        return;
    }
    if (new Date(actEnrollEnd) >= new Date(actStart)) {
        alert("报名截止时间必须早于活动开始时间");
        return;
    }
    if (!actContent) {
        alert("请填写活动详情");
        return;
    }

    // 请求参数
    const submitData = {
        clubId: 1, // 硬编码！而非读取登录用户的社团ID
        title: actTitle,
        startTime: actStart,
        endTime: actEnd,
        location: actLoc,
        maxNum: Number(actMax),
        enrollEndTime: actEnrollEnd,
        content: actContent
    };

    try {
        const res = await axios.post(`${BASE_URL}/activity/add`, submitData);
        const resp = res.data;
        if (resp.code === 200) {
            alert("活动发布成功！");
            // 清空表单
            document.getElementById('actTitle').value = "";
            document.getElementById('actStart').value = "";
            document.getElementById('actEnd').value = "";
            document.getElementById('actLoc').value = "";
            document.getElementById('actMax').value = "";
            document.getElementById('actEnrollEnd').value = "";
            document.getElementById('actContent').value = "";
            // 跳转活动列表
            await loadPageHtml("activityList");
            loadActivityList();
            document.querySelectorAll(".nav-menu").forEach(i=>i.classList.remove("active"));
            document.querySelector('[data-page="activityList"]').classList.add("active");
        } else {
            alert("发布失败：" + resp.msg);
        }
    } catch (err) {
        console.error("发布请求异常：", err);
    }
}