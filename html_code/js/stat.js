async function loadStatData(){
    let url = userRole === "ADMIN" ? "/stat/admin" : "/stat/leader";
    let res = await axios.get(`${BASE_URL}${url}`);
    let box = document.getElementById("statBox");
    if(userRole === "ADMIN"){
        let d = res.data.data;
        box.innerHTML = `
            <div class="card">
                <h4>全平台统计</h4>
                <p>活动总数：${d.totalActivity}</p>
                <p>报名总人次：${d.totalEnroll}</p>
                <p>社团总数：${d.totalClub}</p>
            </div>
        `;
    }else{
        let list = res.data.data;
        let html = `<div class="card"><h4>本社团活动统计</h4>`;
        list.forEach(item=>{
            html += `
                <div class="border">
                    <p>活动：${item.activityTitle}</p>
                    <p>报名人数：${item.enrollCount}</p>
                    <p>签到人数：${item.signCount}</p>
                </div>
            `
        })
        box.innerHTML = html;
    }
}