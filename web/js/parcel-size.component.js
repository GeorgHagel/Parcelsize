// Register `phoneList` component, along with its associated controller and template
angular.
  module('parcelConfig').
  component('parcelSize', {
	  transclude: true,
	  template:
		  '<div class="w3-card-2">'+
		  '<div class="w3-container w3-teal">'+
		    '<h2>Paketgröße</h2>'+
		  '</div>'+

		  '<form class="w3-container">'+
	
		  '<label>Länge</label>'+
		  '<input class="w3-input" id="cfg-size-length" type="text" ng-model="$ctrl.parcel.length">'+
	
		  '<label>Breite</label>'+
		  '<input class="w3-input" id="cfg-size-height" type="text" ng-model="$ctrl.parcel.height">'+
		 
		  '<label>Tiefe</label>'+
		  '<input class="w3-input" id="cfg-size-depth" type="text" ng-model="$ctrl.parcel.depth">'+
		  
		  '<br />'+
		  '<button id="cfg-size-button" class="w3-btn w3-teal" ng-click="$ctrl.calcSize($ctrl.parcel)">Größe berechnen</button><label id="cfg-size-res">Paketgröße: {{$ctrl.parcel.size}}</label> '+
		  '</div>' +
		  '</form>',
    	
    controller: function ParcelSizeController($rootScope, $http) {
      this.parcel = 
        {
          length: 0,
          height: 0,
          depth: 0,
          size: ''
        };
      this.parcelsize = ''; 
      $rootScope.parcelsize =this.parcel;
      
      $rootScope.size = this.size;
      
      this.calcSize = function($parcel){
    	  console.log(JSON.stringify(this.parcel));
    	
    	 $http({
    		    url: "http://192.168.56.102:1100/parcel/sent/size",
    		    dataType: "json",
    		    method: "POST",
    		    data: JSON.stringify(this.parcel),
    		    headers: {
    		        "Content-Type": "text/plain"
    		    }
    		}).then(function(response){
    			console.log(response.data.size);
    			$parcel.size = response.data.size;
    		});
    	 
      }
     
    }
  });
  
 
  
