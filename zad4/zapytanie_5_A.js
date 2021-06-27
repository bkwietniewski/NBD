//5.	Średnia i łączna ilość środków na kartach kredytowych kobiet narodowości polskiej w podziale na waluty. ,
var result = db.people.aggregate([
    { $match: { nationality: "Poland", sex: "Female" } },
    { $unwind: "$credit" },
    {
        $group: {
            _id: "$credit.currency",
            avg_balance: { "$avg": { $toDouble: "$credit.balance" } },
            sum_balance: { "$sum": { $toDouble: "$credit.balance" } }
        }
    }
])
printjson(result.toArray())