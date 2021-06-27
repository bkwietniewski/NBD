//1.	Średnią wagę i wzrost osób w bazie z podziałem na płeć (tzn. osobno mężczyzn, osobno kobiet); 
var result = db.people.aggregate({
    $group: {
        _id: "$sex",
        avg_height: { "$avg": { $toDouble: "$height" } },
        avg_weight: { "$avg": { $toDouble: "$weight" } }
    }
})
printjson(result.toArray())