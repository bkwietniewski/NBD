//4.	Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości; 
var result = db.people.aggregate([
    {
        $addFields: {
            bmi: {
                $divide: [
                    { $toDouble: "$weight" },
                    {
                        $pow: [{ $divide: [{ $toDouble: "$height" }, 100] }, 2]
                    }]
            }
        }
    },
    {
        $group: {
            _id: "$nationality",
            avg_bmi: { "$avg": "$bmi" },
            min_bmi: { "$min": "$bmi" },
            max_bmi: { "$max": "$bmi" }
        }
    }
])
printjson(result.toArray())