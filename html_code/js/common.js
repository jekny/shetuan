const BASE_URL = "http://localhost:8080";
let token = localStorage.getItem("token") || "";
let userRole = localStorage.getItem("role") || "";
let currentActId = null;

// axios全局携带token
axios.interceptors.request.use(config => {
    if(token) config.headers.token = token;
    return config;
})

window.onload = async function(){
    if(token){
        showMainPage();
        toggleRoleMenu();
        // 默认加载活动首页
        await loadPageHtml("activityList");
        loadActivityList();
    }
    bindSidebarClick();
}

// 侧边栏点击绑定
function bindSidebarClick(){
    document.querySelectorAll(".nav-menu").forEach(item=>{
        item.onclick = async function(e){
            e.preventDefault();
            let pageId = this.dataset.page;
            document.querySelectorAll(".nav-menu").forEach(i=>i.classList.remove("active"));
            this.classList.add("active");
            await loadPageHtml(pageId);
            // 对应页面加载数据
            switch(pageId){
                case "activityList": loadActivityList(); break;
                case "myEnroll": loadMyEnroll(); break;
                case "clubList": loadClubList(); break;
                case "statPage": loadStatData(); break;
            }
        }
    })
}

// 异步加载pages文件夹html片段到内容区
async function loadPageHtml(pageName){
    let res = await fetch(`./pages/${pageName}.html`);
    let html = await res.text();
    document.getElementById("contentContainer").innerHTML = html;
}

// 异步加载弹窗模板
async function loadModalHtml(modalName){
    let res = await fetch(`./modal/${modalName}.html`);
    let html = await res.text();
    document.getElementById("modalContainer").innerHTML = html;
}

// 切换登录/注册标签
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

// 展示主页面、隐藏登录页
function showMainPage(){
    document.getElementById("loginPage").classList.remove("active");
    document.getElementById("mainPage").classList.add("active");
}

// 根据角色隐藏/显示负责人菜单
function toggleRoleMenu(){
    let publishMenu = document.getElementById("menuPublish");
    let statMenu = document.getElementById("menuStat");
    if(userRole === "STUDENT"){
        publishMenu.style.display = "none";
        statMenu.style.display = "none";
    }else{
        publishMenu.style.display = "block";
        statMenu.style.display = "block";
    }
}

// 退出登录
function logout(){
    localStorage.clear();
    token = "";
    userRole = "";
    document.getElementById("loginPage").classList.add("active");
    document.getElementById("mainPage").classList.remove("active");
    document.getElementById("contentContainer").innerHTML = "";
}