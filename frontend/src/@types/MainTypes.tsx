export interface Suggestion {
    value: string;
    data: {
        fias_id: string;
    };
}

export interface SuggestionsResponse {
    suggestions: Suggestion[];
}