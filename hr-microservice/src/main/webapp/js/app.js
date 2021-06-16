class HrViewModel {
    constructor(){
        // domain related observables
        this.events= ko.observableArray([]);
        // now make a ws connection to the server
        this.socket = new WebSocket("ws://localhost:5200/hr/api/v1/events");
		this.socket.addEventListener('open',(event)=>{
	  		console.log("Connected to the server!"); 
   		});
   		this.socket.addEventListener('message', (message)=>{
	   		let event = JSON.parse(message.data);
	   		this.events.push(event);
   		});
        
    }


};

var hrViewModel= new HrViewModel();

$( () => {
  ko.applyBindings(hrViewModel);
});
