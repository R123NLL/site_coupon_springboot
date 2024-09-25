import React, { useState } from "react";

export default function FilterComponent() {

    const [selectedOption, setSelectedOption] = useState('');

    const handleOptionChange = (event) => {
        console.log(event.target);
        console.log(selectedOption);
        setSelectedOption(event.target.value);
        console.log(selectedOption);
        
    };

    const updateFilter = (event) => {
        //console.log(`${selectedOption}/${event.target.value}`);
    }

    return (
        <div className="container">
            <div className="form-check form-check-inline">
                <input
                    className="form-check-input"
                    type="radio"
                    name="inlineRadioOptions"
                    id="inlineRadio1"
                    value="category"
                    onChange={handleOptionChange}
                />
                <label className="form-check-label" htmlFor="inlineRadio1">
                    By Category
                </label>
            </div>
            <div className="form-check form-check-inline">
                <input
                    className="form-check-input"
                    type="radio"
                    name="inlineRadioOptions"
                    id="inlineRadio2"
                    value="price"
                    onChange={handleOptionChange}
                />
                <label className="form-check-label" htmlFor="inlineRadio2">
                    Max Price
                </label>
            </div>
            {
                selectedOption === 'price' && <input type="number" name="price" onChange={updateFilter} />
            }
        </div>
    );
}