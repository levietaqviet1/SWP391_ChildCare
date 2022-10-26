//const body = document.querySelector("body"),
//        modeToggle = body.querySelector(".mode-toggle");
//sidebar = body.querySelector("nav");
//sidebarToggle = body.querySelector(".sidebar-toggle");
//
//let getMode = localStorage.getItem("mode");
//if (getMode && getMode === "dark") {
//    body.classList.toggle("dark");
//}
//
//let getStatus = localStorage.getItem("status");
//if (getStatus && getStatus === "close") {
//    sidebar.classList.toggle("close");
//}
//
//modeToggle.addEventListener("click", () => {
//    body.classList.toggle("dark");
//    if (body.classList.contains("dark")) {
//        localStorage.setItem("mode", "dark");
//    } else {
//        localStorage.setItem("mode", "light");
//    }
//});
//
//sidebarToggle.addEventListener("click", () => {
//    sidebar.classList.toggle("close");
//    if (sidebar.classList.contains("close")) {
//        localStorage.setItem("status", "close");
//    } else {
//        localStorage.setItem("status", "open");
//    }
//});

function doDelete(id) {
    if (confirm("Are you sure to delete this schedule id?")) {
        window.location = "deleteschedule?sid=" + id;
    }
}

function doUpdate(id_update, id_old) {
//    alert(id_update);
//    alert(id_old);
    const myElement1 = document.getElementById(id_update);
    myElement1.style.display = "block";
    const myElement2 = document.getElementById(id_old);
    myElement2.style.display = "none";
}

function cancelUpdate(id_update, id_old) {
    const myElement1 = document.getElementById(id_update);
    myElement1.style.display = "none";
    const myElement2 = document.getElementById(id_old);
    myElement2.style.display = "block";
}

function changeDate() {
    var e = document.getElementById("ddlViewBy");
    var strUser = e.value;
    document.getElementById("ddlViewByinput").value = strUser;
    console.log(strUser);
}


