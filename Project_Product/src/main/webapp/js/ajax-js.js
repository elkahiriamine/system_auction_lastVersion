function affichage(){
    xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET","listEncheresAll.jee",false);
    xmlhttp.send(null);
    
}

var myVar = setInterval(myTimer, 5000);
function myTimer() {
  affichage()
  location.reload();
}



