<script type=text/javascript>

    function isLeapYear(iYear) {
        return ((iYear % 4 == 0 && iYear % 100 != 0) || iYear % 400 == 0);
    }
    function isDate(sDate){
        var sSeparator = '/';
        if(!sDate.match("^[0-9]{2}/[0-9]{2}/[0-9]{4}$")) return false;
        var arDate = sDate.split(sSeparator);
        var iDay = parseInt(arDate[0]);
        var iMonth = parseInt(arDate[1]);
        var iYear = parseInt(arDate[2]);
        var arDayPerMonth = [31,(isLeapYear(iYear))?29:28,31,30,31,30,31,31,30,31,30,31];
        if(!arDayPerMonth[iMonth-1]) return false;
            return (iDay <= arDayPerMonth[iMonth-1] && iDay > 0);
    }
</script>