import { PersonDto } from "../../generated_sources";

export const personDtoListMock: PersonDto[] = [
  {
    id: 1,
    firstname: "Max",
    lastname: "Mustermann",
    colour: "lila",
    zipcode: "13503",
    city: "Berlin"
  },
  {
    id: 2,
    firstname: "Maria",
    lastname: "Musterfamilie",
    colour: "rot",
    zipcode: "16761",
    city: "Hennigsdorf"
  },
  {
    id: 3,
    firstname: "Martha",
    lastname: "Musterfamilie",
    colour: "rot",
    zipcode: "16761",
    city: "Hennigsdorf"
  },
  {
    id: 4,
    firstname: "Hans",
    lastname: "Jürgens",
    colour: "gelb",
    zipcode: "13507",
    city: "Berlin"
  }
];

export const colourListMock: string[] = [
  "gelb",
  "rot",
  "lila"
]