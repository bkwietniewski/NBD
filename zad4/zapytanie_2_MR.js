//2.	Łączną ilość środków pozostałych na kartach kredytowych osób w bazie, w podziale na waluty; 
var mapFunction = function() {
    this.credit.forEach(e => emit(e.currency, parseFloat(e.balance)) )
}
var reduceFunction = function(keySex, countObjVals) {
    return Array.sum(countObjVals)
}

var result = db.people.mapReduce(
    mapFunction, 
    reduceFunction, 
    {
        out: { inline: 1 }
    })
printjson(result)