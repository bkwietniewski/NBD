//10.	Usuń u wszystkich osób o zawodzie „Editor” własność „email”. 
var result = db.people.updateMany({ job: "Editor" }, { $unset: { email: "" } })
printjson(result)
