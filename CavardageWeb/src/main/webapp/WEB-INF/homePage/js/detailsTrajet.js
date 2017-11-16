<script type=text/javascript>
function saveAttribute(idEtape,nomEtape){
    document.myForm.afficherEtape.value = nomEtape;
    document.myForm.etapeArrivee.value = idEtape;
    document.myForm.afficherEtape.type = "text";
    document.getElementById("label_etape").style.display = 'block';
    document.myForm.supprimerEtape.type = "button";
}

function supprimerChampsEtape(){
    document.myForm.supprimerEtape.type = "hidden";
    document.myForm.afficherEtape.type = "hidden";
    document.getElementById("label_etape").style.display = 'none';
    document.myForm.etapeArrivee.value = null;
}

function verifPlacesRestantes(champsReserve, champsRestant) {
    if(isNaN(champsReserve)){
        document.getElementById("messageErreur").innerHTML = 'Rentrez un nombre dans le champs pour le nombre de places';
        document.getElementById("messageErreurDiv").style.display = 'block';
        return false;
    }
    if(+champsReserve <= 0) {
        document.getElementById("messageErreur").innerHTML = 'Le nombre de places réservées doit être supérieur à zéro';
        document.getElementById("messageErreurDiv").style.display = 'block';
        return false;
    }
    if(+champsReserve > +champsRestant) {
        document.getElementById("messageErreur").innerHTML = 'Le nombre de places réservées doit être inférieur à celui des places disponibles';
        document.getElementById("messageErreurDiv").style.display = 'block';
        return false;
    }
    return true;
}

function verifForm(){
    return verifPlacesRestantes(document.myForm.nbPlacesReservees.value,document.myForm.placesRestantes.value);
}

</script>