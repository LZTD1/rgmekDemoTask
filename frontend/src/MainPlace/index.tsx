import React, {useState} from "react";
// @ts-ignore
import axios from "axios";
// @ts-ignore
import debounce from 'lodash.debounce';

import styles from "./mainPlace.module.scss";
import {AddressData, SuggestionsResponse} from "../@types/MainTypes";

function MainPlace() {
    const [suggestions, setSuggestions] = useState<SuggestionsResponse>({suggestions: []});
    const [address, setAddress] = useState<AddressData[]>([]);

    const fetchSuggestions = async (address: string) => {
        try {
            let config = {
                method: 'post',
                maxBodyLength: Infinity,
                url: 'http://localhost:8080/suggest?address=' + encodeURIComponent(address),
                headers: {}
            };
            axios.request(config)
                .then((response) => {
                    setSuggestions(response.data);
                    setAddress([]);
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
            } else {
                setSuggestions({suggestions: []});
                setAddress([]);
            }
        }, 250),
        []
    );

    const handleItemClick = async (fiasId: string) => {
        try {
            let config = {
                method: 'post',
                url: `http://localhost:8080/findByFias?fias=` + encodeURIComponent(fiasId), headers: {}
            };
            axios.request(config)
                .then((response) => {
                    setSuggestions({suggestions: []});
                    setAddress(response.data)
                    console.log(response.data)
                })
                .catch((error) => {
                    console.log(error);
                    alert('Такого адреса нет в базе данных.');
                });
        } catch (error) {
            console.error("Error fetching data from /findByFias:", error);
        }
    };

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
                        <div
                            className={styles.item}
                            key={index}
                            onClick={() => handleItemClick(suggestion.data.fias_id)}
                        >{suggestion.value}</div>
                    ))}

                </div>
            )}
            {address.length > 0 && (
                <div>
                    <table>
                        <thead>
                        <tr>
                            <th>Code</th>
                            <th>Postindex</th>
                            <th>Punktnm</th>
                            <th>Distnm</th>
                            <th>Streetnm</th>
                            <th>Houseno</th>
                            <th>Housepostfix</th>
                            <th>Housename</th>
                            <th>Numberofkvart</th>
                            <th>Korpusno</th>
                            <th>Korpuspostfix</th>
                            <th>Korpusname</th>
                            <th>Floorcount</th>
                            <th>Flatno</th>
                            <th>Flatpostfix</th>
                            <th>Flatname</th>
                            <th>Roomno</th>
                            <th>Roompostfix</th>
                            <th>Roomname</th>
                            <th>Fias</th>
                            <th>Search</th>
                            <th>Adressfull</th>
                            <th>Kadastr</th>
                        </tr>
                        </thead>
                        <tbody>
                        {address.map((item, index) => (
                            <tr key={index}>
                                <td>{item.code}</td>
                                <td>{item.postindex}</td>
                                <td>{item.punktnm}</td>
                                <td>{item.distnm}</td>
                                <td>{item.streetnm}</td>
                                <td>{item.houseno}</td>
                                <td>{item.housepostfix}</td>
                                <td>{item.housename}</td>
                                <td>{item.numberofkvart}</td>
                                <td>{item.korpusno}</td>
                                <td>{item.korpuspostfix}</td>
                                <td>{item.korpusname}</td>
                                <td>{item.floorcount}</td>
                                <td>{item.flatno}</td>
                                <td>{item.flatpostfix}</td>
                                <td>{item.flatname}</td>
                                <td>{item.roomno}</td>
                                <td>{item.roompostfix}</td>
                                <td>{item.roomname}</td>
                                <td>{item.fias}</td>
                                <td>{item.search}</td>
                                <td>{item.adressfull}</td>
                                <td>{item.kadastr}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
}

export default MainPlace;
