export interface IGnome {
    id: number;
    name: string;
    thumbmail: string;
    weight: number;
    height: number;
    hair_color: string;
    friend: [string];
}

export class Gnome implements IGnome {
    constructor(
        public id: number,
        public name: string,
        public thumbmail: string,
        public weight: number,
        public height: number,
        public hair_color: string,
        public friend: [string]
    ) {}
}
