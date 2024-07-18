export interface Suggestion {
    value: string;
    data: {
        fias_id: string;
    };
}

export interface SuggestionsResponse {
    suggestions: Suggestion[];
}
export interface AddressData {
    code: string;
    postindex: string;
    punktnm: string;
    distnm: string;
    streetnm: string;
    houseno: string;
    housepostfix: string | null;
    housename: string | null;
    numberofkvart: number;
    korpusno: string | null;
    korpuspostfix: string | null;
    korpusname: string | null;
    floorcount: number;
    flatno: number;
    flatpostfix: string;
    flatname: string | null;
    roomno: string | null;
    roompostfix: string | null;
    roomname: string | null;
    fias: string;
    search: string;
    adressfull: string;
    kadastr: string | null;
}