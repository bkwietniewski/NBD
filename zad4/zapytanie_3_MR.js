//3.	Listę unikalnych zawodów; 
var mapFunction = function () {
    emit(this.job, "");
}
var reduceFunction = function (job, countObjVals) {
    return job;
}

var result = db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {
        out: { inline: 1 }
    })
printjson(result)