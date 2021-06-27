//3.	Listę unikalnych zawodów; 
var result = db.people.distinct("job")
printjson(result)