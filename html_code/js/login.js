async function login(){
    let username = document.getElementById("loginUsername").value.trim();
    let pwd = document.getElementById("loginPwd").value.trim();
    if(!username || !pwd) return alert("账号密码不能为空");
    try{
        const res = await axios.post(`${BASE_URL}/user/login`,{username,password:pwd});
        if(res.data.code === 200){
            token = res.data.data;
            localStorage.setItem("token",token);
            const payloadStr = token.split('.')[1];
            const payload = JSON.parse(atob(payloadStr));
            localStorage.setItem("role",payload.role);
            userRole = payload.role;
            console.log("登录完成，更新角色为：", userRole);
            alert("登录成功");
            showMainPage();
            toggleRoleMenu();
            await loadPageHtml("activityList");
            loadActivityList();
        }else{
            alert(res.data.msg);
        }
    }catch(err){
        alert("后端未启动或网络异常");
    }
}

async function register(){
    const data = {
        username: document.getElementById("regUsername").value.trim(),
        realName: document.getElementById("regName").value.trim(),
        role: document.getElementById("regRole").value,
        password: document.getElementById("regPwd").value.trim()
    }
    if(!data.username || !data.realName || !data.password) return alert("完整填写表单");
    const res = await axios.post(`${BASE_URL}/user/register`,data);
    alert(res.data.msg);
}