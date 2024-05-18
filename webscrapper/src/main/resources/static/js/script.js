function onClickSearch(){
    let text = document.getElementById("textInputSearch").value
    location.href='./results.html?query='+text
}