import React from 'react';
import axios from 'axios';

class App extends React.Component{

  constructor(props){
    super(props);
    this.state = {
      employees : []
    }
  }
  

  componentDidMount(){
    console.log("Component Mounted");
    axios.get('localhost:9090/employees').then(
        (res)=>{
          console.log(res.data);
          this.setState({
            employees : res.data
          });
        }
      );
  }

  render(){

      const empsList = this.state.employees.length > 0 ? (
        this.state.employees.map((emp)=>{
           <div key={emp.id}>
              {emp.lastName}
           </div>
        })
       
        ) : (
        <p>No employees yet!!!</p>
        );
     return (
      <div className="App">
        
      </div>
     );
  }
 
}

export default App;
