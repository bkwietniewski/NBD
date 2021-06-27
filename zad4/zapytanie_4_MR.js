//4.	Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości; 
var mapFunction = function () {
    var weight = parseFloat(this.weight)
    var heightInMeters = parseFloat(this.height) / 100
    var bmi = weight / (Math.pow(heightInMeters, 2))
    emit(this.nationality, { "count": 1, "maxBMI": bmi, "minBMI": bmi, "totalBMI": bmi});
}
var reduceFunction = function (keySex, countObjVals) {
    var reducedVal = {"count":0, "maxBMI": 0, "minBMI": 99999, "totalBMI": 0};
    countObjVals.forEach(v => {
        reducedVal.count += v.count;
        reducedVal.maxBMI = Math.max(reducedVal.maxBMI, v.maxBMI);
        reducedVal.minBMI = Math.min(reducedVal.minBMI, v.minBMI);
        reducedVal.totalBMI += v.totalBMI;
    })
    return reducedVal;
}
var finalizeFunction = function(keySex, reducedVal) {
    return { "avgBMI": reducedVal.totalBMI/reducedVal.count, "maxBMI": reducedVal.maxBMI, "minBMI": reducedVal.minBMI };
}

var result = db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {
        out: { inline: 1 },
        finalize: finalizeFunction
    })
printjson(result)