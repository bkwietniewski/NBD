//1.	Średnią wagę i wzrost osób w bazie z podziałem na płeć (tzn. osobno mężczyzn, osobno kobiet); 
var mapFunction = function() {
    emit(this.sex, { "height": parseFloat(this.height), "weight": parseFloat(this.weight), "count": 1} ) 
}
var reduceFunction = function(keySex, countObjVals) {
    var reducedVal = {count:0, height: 0, weight: 0};
    countObjVals.forEach(v => {
        reducedVal.count += v.count;
        reducedVal.height += v.height;
        reducedVal.weight += v.weight;
    })
    return reducedVal;
}
var finalizeFunction = function(keySex, reducedVal) {
    return { "avgHeight": reducedVal.height/reducedVal.count, "avgWeight": reducedVal.weight/reducedVal.count };
}

var result = db.people.mapReduce(
    mapFunction, 
    reduceFunction, 
    {
        out: { inline: 1 },
        finalize: finalizeFunction
    })
printjson(result)