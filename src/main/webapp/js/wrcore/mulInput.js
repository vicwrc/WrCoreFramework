

        function MultipleInput(componentGlobalId) {
            this.componentGlobalId = componentGlobalId;
            this.multipleList = document.getElementById(componentGlobalId);
            this.root = this.multipleList.parentNode;
        }

        MultipleInput.prototype = {

            removeSelected: function () {
                var selectedOptions = this.getSelectedChildren();
                for (x=0;x < selectedOptions.length;x++)
                {
                    this.multipleList.removeChild(selectedOptions[x]);
                    this.addHiddenField(selectedOptions[x].text,'remove');
                }
            },

            add: function () {
                var newValue = document.getElementsByName(this.componentGlobalId+'_fake')[0].value;
                this.multipleList.appendChild(this.createChild(newValue,newValue));
                this.addHiddenField(newValue,'add');
            },

            createChild: function(optionName, optionValue) {
                var optionElement = document.createElement('option');
                optionElement.text = optionName;
                optionElement.value = optionValue;
                return optionElement;
            },

            getHiddenComponentName: function(action) {
                return this.componentGlobalId + '_' + action;
            },
            not: function(action) {
                if('add'.toString() == action.toString()){
                    return 'remove';
                } else {
                    return 'add';
                }
            },

            addHiddenField: function(value, action) {
                var existHiddenParams = this.getHiddenParams(this.not(action));
                for( var x=0; x < existHiddenParams.length; x++){
                    if( existHiddenParams[x].value.toString() == value.toString() ){
                        this.root.removeChild(existHiddenParams[x]);
                        return 'removed';
                    }
                }
                this.root.appendChild(this.createHiddenField(value, action));
                return 'added';
            },

            createHiddenField: function(value, action) {
                var inputElement = document.createElement('input');
                inputElement.type = 'hidden';
                inputElement.name = this.getHiddenComponentName(action);
                inputElement.value = value;
                return inputElement;
            },

            getSelectedChildren: function() {
                var selectedOptions = [];
                for (var x=0; x < this.multipleList.options.length; x++)
                {
                    if (this.multipleList.options[x].selected)
                    {
                        selectedOptions[selectedOptions.length] = this.multipleList.options[x];
                    }
                }
                return selectedOptions;
            },

            getHiddenParams: function(action) {
                return document.getElementsByName(this.getHiddenComponentName(action));
            }
        }