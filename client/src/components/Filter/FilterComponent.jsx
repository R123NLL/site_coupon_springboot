import React, { useState } from "react";

export default function FilterComponent(props) {

    const [selectedOption, setSelectedOption] = useState('');

    const handleOptionChange = (event) => {
        setSelectedOption(event.target.value);
    };

    const updateFilter = (event) => {
        if(event.target.value) {
            const value = event.target.value;
            const filter = {
                filterBy: selectedOption,
                filterValue: isNaN(value) ? value : +value
            }
            if(typeof props.getFilter === 'function')
                props.getFilter(filter);
        }
    }

    const resetFilter = () => {
        if(typeof props.getFilter === 'function')
            props.getFilter({});
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
            <>
                {selectedOption === 'price' && <input type="number" name="price" onChange={updateFilter} />}
                {selectedOption === 'category' && 
                    <select name="categorySelect" id="categorySelect" onChange={updateFilter}>
                    <option value="" defaultValue>Select category</option>
                    <option value="food">Food</option>
                    <option value="electricity">Electricity</option>
                    <option value="clothing">Clothing</option>
                    <option value="vacation">Vacation</option>
                  </select>}
                  {<button type="button" className="btn btn-sm btn-secondary ms-2" onClick={resetFilter}>clear</button>}
            </>
        </div>
    );
}