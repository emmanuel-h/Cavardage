<script type=text/javascript>

function ajouterEtape(){
    var etape = document.getElementById("inputEtape").value;
    var prix = document.getElementById("inputPrix").value;

    var li = document.createElement("LI");
    li.id = etape;

    /*
    var idLi = document.createAttribute("id");
    idLi.value = etape;
    li.setAttributeNode(idLi);
    */

    var textNode = document.createTextNode(etape + "   -   " + prix + " €");
    li.appendChild(textNode);

    var button = document.createElement("BUTTON");
    button.innerHTML = "&nbsp;-&nbsp;";
    button.type = "button";
    button.classList = "btn btn-outline-danger";
    var onclickButton = document.createAttribute("onclick");
    onclickButton.value = "supprimerEtape('"+etape+"')";
    button.setAttributeNode(onclickButton);
    li.appendChild(button);

    document.getElementById("ulEtapes").appendChild(li);
}
function supprimerEtape(nomEtape){
    var n = document.getElementById(nomEtape);
    n.parentNode.removeChild(n);
    //document.documentElement.removeChild(n);
    //nomEtape.documentElement.removeChild(nomEtape);
    /*
    var div = document.getElementById(nomEtape);
    div.parentNode.removeChild(div);
    */
}

</script>