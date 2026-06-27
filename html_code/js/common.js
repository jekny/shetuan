const BASE_URL = "http://localhost:8080";
let token = localStorage.getItem("token") || "";
let userRole = localStorage.getItem("role") || "";
let currentActId = null;

axios.interceptors.request.use(config => {
    if(token) config.headers.token = token;
    return config;
})
axios.interceptors.response.use(
    res => res,
    err => {
        localStorage.clear();
        token = "";
        userRole = "";
        alert("后端服务断开，请重启服务刷新页面");
        document.getElementById("loginPage").classList.add("active");
        document.getElementById("mainPage").classList.remove("active");
        document.getElementById("contentContainer").innerHTML = "";
        return Promise.reject(err);
    }
)

window.onload = async function(){
    token = localStorage.getItem("token") || "";
    userRole = localStorage.getItem("role") || "";
    console.log("页面初始化读取角色：", userRole);
    // 全局委托监听侧边栏点击，永久生效
    document.querySelector(".sidebar").addEventListener("click", async function(e){
        const target = e.target.closest(".nav-menu");
        if(!target) return;
        e.preventDefault();
        const pageId = target.dataset.page;
        console.log("点击菜单，要加载页面：", pageId);
        document.querySelectorAll(".nav-menu").forEach(i=>i.classList.remove("active"));
        target.classList.add("active");
        await loadPageHtml(pageId);
        // 页面加载完成后执行对应数据加载
        switch(pageId){
            case "activityList": loadActivityList(); break;
            case "myEnroll": loadMyEnroll(); break;
            case "clubList": loadClubList(); break;
            case "statPage": loadStatData(); break;
            case "publishAct": console.log("进入发布活动页面"); break;
        }
    })

    if(token){
        showMainPage();
        toggleRoleMenu();
        await loadPageHtml("activityList");
        loadActivityList();
    }
}

async function loadPageHtml(pageName){
    console.log("开始拉取页面文件：pages/" + pageName + ".html");
    try {
        const res = await fetch(`./pages/${pageName}.html`);
        if (!res.ok) {
            alert(`页面文件${pageName}.html缺失，请检查pages文件夹`);
            return;
        }
        const html = await res.text();
        document.getElementById("contentContainer").innerHTML = html;
        console.log(pageName + "页面加载完成");
    } catch (err) {
        console.error("页面拉取失败", err);
        alert("页面文件加载失败，检查文件是否存在");
    }
}

async function loadModalHtml(modalName){
    const res = await fetch(`./modal/${modalName}.html`);
    const html = await res.text();
    document.getElementById("modalContainer").innerHTML = html;
}

function switchTab(type){
    if(type === 'login'){
        document.getElementById("loginForm").style.display = "block";
        document.getElementById("registerForm").style.display = "none";
        document.querySelectorAll("#tab button")[0].classList.add("active");
        document.querySelectorAll("#tab button")[1].classList.remove("active");
    }else{
        document.getElementById("loginForm").style.display = "none";
        document.getElementById("registerForm").style.display = "block";
        document.querySelectorAll("#tab button")[1].classList.add("active");
        document.querySelectorAll("#tab button")[0].classList.remove("active");
    }
}

function showMainPage(){
    document.getElementById("loginPage").classList.remove("active");
    document.getElementById("mainPage").classList.add("active");
}

//验证角色 展示对应页面
function toggleRoleMenu(){
    const publishMenu = document.getElementById("menuPublish");
    const statMenu = document.getElementById("menuStat");
    console.log("当前登录角色：",userRole);
    if(userRole === "STUDENT"){
        publishMenu.style.display = "none";
        statMenu.style.display = "none";
    }else{
        publishMenu.style.display = "block";
        statMenu.style.display = "block";
        publishMenu.style.pointerEvents = "auto";
        statMenu.style.pointerEvents = "auto";
    }
}

function logout(){
    localStorage.clear();
    token = "";
    userRole = "";
    document.getElementById("loginPage").classList.add("active");
    document.getElementById("mainPage").classList.remove("active");
    document.getElementById("contentContainer").innerHTML = "";
}