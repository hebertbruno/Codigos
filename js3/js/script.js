/**
 * Created by fabiano on 09/05/2017.
 */
function geraGrafico() {
    var h = document.getElementsByClassName('altura');
    var w =  document.getElementById("w1").value;

    for(var i=0;i<5;i++){
        var b = document.createElement("DIV");
        b.style.display = 'inline-block';
        b.style.marginTop = '0.5em';
        b.style.marginRight = '0.5em';
        b.style.backgroundColor = 'red';
        b.style.width = w+'px';
        b.style.height = h[i].value+'px';
        document.body.appendChild(b);
    }
}