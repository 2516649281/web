// JavaScript Document By lishewen
var theTable = document.getElementById("tablelsw");
var totalPage = document.getElementById("spanTotalPage");
var pageNum = document.getElementById("spanPageNum");

var spanPre = document.getElementById("spanPre");
var spanNext = document.getElementById("spanNext");
var spanFirst = document.getElementById("spanFirst");
var spanLast = document.getElementById("spanLast");

var totalPaget = document.getElementById("spanTotalPaget");
var pageNumt = document.getElementById("spanPageNumt");

var spanPret = document.getElementById("spanPret");
var spanNextt = document.getElementById("spanNextt");
var spanFirstt = document.getElementById("spanFirstt");
var spanLastt = document.getElementById("spanLastt");

var numberRowsInTable = theTable.rows.length;
var pageSize = 20;
var page = 1;

//下一页
function next() {

    hideTable();

    currentRow = pageSize * page;
    maxRow = currentRow + pageSize;
    if (maxRow > numberRowsInTable) maxRow = numberRowsInTable;
    for (var i = currentRow; i < maxRow; i++) {
        theTable.rows[i].style.display = '';
    }
    page++;

    if (maxRow == numberRowsInTable) {
        nextText();
        lastText();
    }
    showPage();
    preLink();
    firstLink();
}

//上一页
function pre() {

    hideTable();

    page--;

    currentRow = pageSize * page;
    maxRow = currentRow - pageSize;
    if (currentRow > numberRowsInTable) currentRow = numberRowsInTable;
    for (var i = maxRow; i < currentRow; i++) {
        theTable.rows[i].style.display = '';
    }


    if (maxRow == 0) {
        preText();
        firstText();
    }
    showPage();
    nextLink();
    lastLink();
}

//第一页
function first() {
    hideTable();
    page = 1;
    for (var i = 0; i < pageSize; i++) {
        theTable.rows[i].style.display = '';
    }
    showPage();

    preText();
    nextLink();
    lastLink();
}

//最后一页
function last() {
    hideTable();
    page = pageCount();
    currentRow = pageSize * (page - 1);
    for (var i = currentRow; i < numberRowsInTable; i++) {
        theTable.rows[i].style.display = '';
    }
    showPage();

    preLink();
    nextText();
    firstLink();
}

function hideTable() {
    for (var i = 0; i < numberRowsInTable; i++) {
        theTable.rows[i].style.display = 'none';
    }
}

function showPage() {
    pageNum.innerHTML = page;
    pageNumt.innerHTML = page;
}

//总共页数
function pageCount() {
    var count = 0;
    if (numberRowsInTable % pageSize != 0) count = 1;
    return parseInt(numberRowsInTable / pageSize) + count;
}

//显示链接
function preLink() {
    spanPre.innerHTML = "<a href='javascript:pre();'><input type='button' value='上一页' class='ye_but'/></a>";
    spanPret.innerHTML = "<a href='javascript:pre();'><input type='button' value='上一页' class='ye_but'/></a>";
}

function preText() {
    spanPre.innerHTML = "<input type='button' value='上一页' class='ye_but'/>";
    spanPret.innerHTML = "<input type='button' value='上一页' class='ye_but'/>";
}

function nextLink() {
    spanNext.innerHTML = "<a href='javascript:next();'><input type='button' value='下一页' class='ye_but'/></a>";
    spanNextt.innerHTML = "<a href='javascript:next();'><input type='button' value='下一页' class='ye_but'/></a>";
}

function nextText() {
    spanNext.innerHTML = "<input type='button' value='下一页' class='ye_but'/>";
    spanNextt.innerHTML = "<input type='button' value='下一页' class='ye_but'/>";
}

function firstLink() {
    spanFirst.innerHTML = "<a href='javascript:first();'><input type='button' value='第一页' class='ye_but'/></a>";
    spanFirstt.innerHTML = "<a href='javascript:first();'><input type='button' value='第一页' class='ye_but'/></a>";
}

function firstText() {
    spanFirst.innerHTML = "<input type='button' value='第一页' class='ye_but'/>";
    spanFirstt.innerHTML = "<input type='button' value='第一页' class='ye_but'/>";
}

function lastLink() {
    spanLast.innerHTML = "<a href='javascript:last();'><input type='button' value='最后一页' class='ye_but'></a>";
    spanLastt.innerHTML = "<a href='javascript:last();'><input type='button' value='最后一页' class='ye_but'></a>";
}

function lastText() {
    spanLast.innerHTML = "<input type='button' value='最后一页' class='ye_but'/>";
    spanLastt.innerHTML = "<input type='button' value='最后一页' class='ye_but'/>";
}

//隐藏表格
function hide() {
    for (var i = pageSize; i < numberRowsInTable; i++) {
        theTable.rows[i].style.display = 'none';
    }

    totalPage.innerHTML = pageCount();
    pageNum.innerHTML = '1';

    totalPaget.innerHTML = pageCount();
    pageNumt.innerHTML = '1';

    nextLink();
    lastLink();
}

hide();


//每隔一段时间检测用户是否操作
var count = 0;
var outTime = 10; //分钟
window.setInterval(go, 1000);

function go() {
    count++;
    if (count === outTime * 60) {
        alert("您因长期未操作导致账号过期，请重新登录!");
        sessionStorage.clear()
        window.close();
        window.location.href = "Login.jsp"
    }
}

var x;
var y;
//监听鼠标
document.onmousemove = function (event) {
    var x1 = event.clientX;
    var y1 = event.clientY;
    if (x !== x1 || y !== y1) {
        count = 0;
    }
    x = x1;
    y = y1;
};

//监听键盘
document.onkeydown = function () {
    count = 0;
};