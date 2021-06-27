//9.	Dodaj do wszystkich osób o imieniu Antonio własność „hobby” o wartości „pingpong”; 
var result = db.people.updateMany({ first_name: "Antonio" }, { $set: { hobby: "pingpong" } })
printjson(result)
