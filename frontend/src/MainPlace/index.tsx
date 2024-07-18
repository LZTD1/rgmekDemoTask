import React, {useState} from "react";
// @ts-ignore
import axios from "axios";
// @ts-ignore
import debounce from 'lodash.debounce';

import styles from "./mainPlace.module.scss";
import {SuggestionsResponse} from "../@types/MainTypes";

function MainPlace() {
    const [suggestions, setSuggestions] = useState<SuggestionsResponse>({ suggestions: [] });

    const fetchSuggestions = async (address: string) => {
        try {
            let config = {
                method: 'post',
                maxBodyLength: Infinity,
                url: 'http://localhost:8080/suggest?address=' + encodeURIComponent(address),
                headers: { }
            };
            axios.request(config)
                .then((response) => {
                    setSuggestions(response.data);
                    console.log(suggestions.suggestions)
                })
                .catch((error) => {
                    console.log(error);
                });

        } catch (error) {
            console.error("Error fetching suggestions:", error);
        }
    };


    const debouncedOnChangeInput = React.useCallback(
        debounce((value: string) => {
            if (value.trim() !== '') {
                fetchSuggestions(value)
            }else{
                setSuggestions( { suggestions: [] } );
            }
        }, 250),
        []
    );

    const onChangeInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        const newValue = event.target.value;
        debouncedOnChangeInput(newValue);
    };

    return (
        <div className={styles.mainPlace}>
            <input
                placeholder={"Напишите город"}
                onChange={onChangeInput}
            />
            {suggestions.suggestions.length > 0 && (
                <div className={styles.list}>
                    {suggestions.suggestions.map((suggestion, index) => (
                        <div className={styles.item} key={index}>{suggestion.value}</div>
                    ))}
                </div>
            )}
        </div>
    );
}

export default MainPlace;
