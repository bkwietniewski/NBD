//7.	Usuń z bazy osoby o wzroście przekraczającym 190;
var result = db.people.remove({ height: { $gt: "190" } })
printjson(result)
