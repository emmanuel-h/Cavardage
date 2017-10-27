<script type=text/javascript>

function ajouterEtape(){
    var etape = document.getElementById("inputEtape").value;
    var prix = document.getElementById("inputPrix").value;

    var li = document.createElement("LI");
    li.id = etape;

    var hidden = document.createElement("INPUT");
    li.appendChild(hidden);

    var textNode = document.createTextNode(etape + "   -   " + prix + "€");
    li.appendChild(textNode);

    var button = document.createElement("BUTTON");
    button.innerHTML = "&nbsp;-&nbsp;";
    button.type = "button";
    button.classList = "btn btn-outline-danger";

    var onclickButton = document.createAttribute("onclick");
    onclickButton.value = "supprimerEtape('"+etape+"')";
    button.setAttributeNode(onclickButton);
    li.appendChild(button);

    var name = document.createAttribute("name");
    name.value="etape";
    hidden.setAttributeNode(name);

    var value = document.createAttribute("value");
    value.value = etape+"-"+prix;
    hidden.setAttributeNode(value);

    var hiddenType = document.createAttribute("type");
    hiddenType.value = "hidden";
    hidden.setAttributeNode(hiddenType);

    document.getElementById("ulEtapes").appendChild(li);

    document.getElementById("inputEtape").value = "";
    document.getElementById("inputPrix").value = "";
}
function supprimerEtape(nomEtape){
    var n = document.getElementById(nomEtape);
    n.parentNode.removeChild(n);
}

</script>