export interface IProfession {
    id?: number;
    name?: string;
}

export class Profession implements IProfession {
    constructor(public id?: number, public name?: string) {}
}
