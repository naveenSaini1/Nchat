import { useContext } from 'react'
import './App.css'
import ChatContainer from './components/ChatContainer/ChatContainer'
import Login from './components/Login/Login'
import Register from './components/Register/Register'
import { UserContext } from './contextapi/UserContext'

function App() {
  const {user}=useContext(UserContext);
console.log("helllo this app compement")
  return (
    <>
   
     
     <ChatContainer/>

  <Register/>
   <Login/>
    </>
  )
}

export default App

const data = {
  userId: "ererer",
  email:"naveen@gmail.com",
  userName: "Naveen",
  image: "http://localhost:8080/myavtor.png",
  bio: "i will try best",
  friends: [
    {
      userName: "Naveen",
      email:"sumit@gmail.com",
      image: "http://localhost:8080/myavtor.png",
      bio: "i will try best",
      message:[{messageId:"1",sender:"hello"},]

    }
  ]
}