//5.	Lista imion i nazwisk wszystkich osób znajdujących się w bazie oraz miast, w których mieszkają, ale tylko dla osób urodzonych w XXI wieku;
var result = db.people.find(
    { birth_date: { $gte: new Date('2001-01-01').toISOString(), $lt: new Date('2101-01-01').toISOString() } },
    { first_name: 1, last_name: 1, location: { city: 1 } }
)
printjson(result.toArray())
