//6.	Dodaj siebie do bazy, zgodnie z formatem danych użytych dla innych osób (dane dotyczące karty kredytowej, adresu zamieszkania i wagi mogą być fikcyjne);
var result = db.people.insert({
    sex: "Male",
    first_name: "Bartosz",
    last_name: "Kwietniewski",
    job: "Student",
    email: "jakismojemail@pjwstk.edu.pl",
    location: {
        city: "Warsaw",
        address: {
            streetname: "Ogrodowa",
            streetnumber: "123"
        }
    },
    description: "Creative description",
    height: "180.50",
    weight: "70.66",
    birth_date: "1996-03-23T16:10:58Z",
    nationality: "Poland",
    credit: [
        {
            type: "jcb",
            number: "1111111111111",
            currency: "PLN",
            balance: "300"
        }
    ]
})
printjson(result)
