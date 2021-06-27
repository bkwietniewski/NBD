//5.	Średnia i łączna ilość środków na kartach kredytowych kobiet narodowości polskiej w podziale na waluty. ,
var mapFunction = function () {
    this.credit.forEach(v => {
        var balanceConv = parseFloat(v.balance)
        emit(v.currency, { "totalBalance": balanceConv, "count": 1 });
    });
}
var reduceFunction = function (keySex, countObjVals) {
    var reducedVal = { "totalBalance": 0, "count": 0 };
    countObjVals.forEach(v => {
        reducedVal.count += v.count;
        reducedVal.totalBalance += v.totalBalance;
    })
    return reducedVal;
}
var finalizeFunction = function (keySex, reducedVal) {
    return { "avgBalance": reducedVal.totalBalance / reducedVal.count, "totalBalance": reducedVal.totalBalance };
}

var result = db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {
        out: { inline: 1 },
        query: {
            nationality: 'Poland',
            sex: 'Female'
        },
        finalize: finalizeFunction
    })
printjson(result)