import { createContext, useContext, useEffect, useRef, useState } from "react";
//import SockJS from 'sockjs-client/dist/sockjs';
import SockJS from "sockjs-client/dist/sockjs";
import Stomp from 'stompjs';

export const UserContext = createContext();


const UserContextProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [userMainData, setUserMainData] = useState();
    const [chatContainer, setChatConnection] = useState();
    const [currentUser, setCurrentUser] = useState();
    const [onMessageVar, setOnMessageVar] = useState(null);
    const [seenMessageByOppositeUser, setSeenMessageByOppositeUser] = useState(null);
    const [activeUserOption, setActiveUserOption] = useState(null);

    useEffect(() => {
        if (activeUserOption != null) {
            handleActiveUser();
        }

    }, [activeUserOption])

    useEffect(() => {
        if (user != null) {
            console.log("userssssssssssssssssssssss", user)
            getTheUserFriends();
        }
    }, [user])
    useEffect(() => {
        if (seenMessageByOppositeUser != null) {
            hdandledLocalReadMessage(seenMessageByOppositeUser);
        }

    }, [seenMessageByOppositeUser])

    useEffect(() => {
        if (chatContainer != null) {
            chatContainer.send('/app/activeUser', {}, JSON.stringify(user));

            chatContainer.subscribe(`/topic/${user.userId}`, (response) => {
                let object = JSON.parse(response.body);
                console.log("hello", object);
                console.log('inside the ssciribe method and userMainData', userMainData);
                setOnMessageVar(object);


            })
            chatContainer.subscribe(`/topic/seen/${user.userId}`, (response) => {
                console.log("seennnnnnnnnnnnnnnnnnnnnn", response);
                setSeenMessageByOppositeUser(JSON.parse(response.body));
            })
            chatContainer.subscribe("/topic/active", (response) => {
                setActiveUserOption(JSON.parse(response.body));
            })
        }

    }, [chatContainer])

    useEffect(() => {
        if (onMessageVar != null) {
            console.log("on Message")
            onMessages(onMessageVar, userMainData)
        }
    }, [onMessageVar])

    const suscribeTheUser = () => {
        const socket = new SockJS('http://localhost:8080/ws');
        const stomp = Stomp.over(socket);
        stomp.connect({}, () => {
            console.log('WebSocket connected');
            setChatConnection(stomp);
        });
    }

    const sendMessage = (object) => {
        console.log(object, "sendMessage usercontext method");

        if (chatContainer != null) {
            chatContainer.send('/app/userMessage', {}, JSON.stringify(object));
        }
    }


    const getTheUserFriends = () => {
        console.log("i am calling")
        fetch(`http://localhost:8080/userFriends/${user.userId}`)
            .then((res) => res.json())
            .then((res) => {
                console.log(res, "dfdfdfdfdf");
                setUserMainData(res);
                suscribeTheUser();
            })

    }
    const loginUser = (object) => {
        const header = {
            method: "POST",
            header: {
                'Content-Type': "applicaton/json"
            }
        }
        fetch(`http://localhost:8080/login/${object.emailId}/${object.password}`, header)
            .then((res) => res.json())
            .then((res) => {
                console.log(res);
                setUser(res);
            })
            .catch((error) => {
            })

    }
    const registerUser = (object) => {
        console.log(object);
        const requestOptions = {
            method: 'POST',
            body:object
        };

        fetch(`http://localhost:8080/register`, requestOptions)
            .then((res) => res.json())
            .then((res) => {
                console.log(res);
                setUser(res);
                // getTheUserFriends();

            })
            .catch(error => console.log(error));

    }
    const logOutUser = () => {
        setUser(null);
    }

    const hanledCurretUser = (user) => {
        console.log("handledCurrentUser", userMainData)
        setCurrentUser(user);
        console.log("handledCurrentUser", userMainData)

    }

    const addFriendToUser = (email) => {
        console.log(`http://localhost:8080/addFriend/${user.userId}/${email}`)
        fetch(`http://localhost:8080/addFriend/${user.userId}/${email}`)
            .then(res => res.json())
            .then((res) => {
                console.log(res, "----")
                setUserMainData(res);


            })
    }

    const onMessages = (object, data) => {
        let result = { ...data };
        let friends = result.friends.map((item) => {
            if (item.emailId == object.senderId) {
                item["messages"].push(object);
            }
            return item;
        })
        console.log(friends);
        result["friends"] = friends;
        setUserMainData(result);
        console.log(result, "after tahta", userMainData)
    }

    const updateMessageStatus = (data) => {
        console.log("hello this is updateMessageStatusmetho")

        let obj = {
            senderEmailId: data.emailId,
            receiverEmailId: user.emailId
        }
        const header = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(obj)

        }
        fetch(`http://localhost:8080/user/reedMessage`, header)
            .then((res) => {
                console.log("resssssssssssssssssssssssssssssss", data)
                hdandledLocalReadMessage(data)
            })
            .catch((error) => {
            })
    }
    const hdandledLocalReadMessage = (data) => {
        let result = { ...userMainData };
        let friends = result.friends.map((item) => {
            if (item.emailId == data.emailId) {
                console.log("frend name", item);
                let messages = item.messages.map((msg) => {
                    if (msg.senderId == item.emailId) {
                        console.log("message name", msg);
                        msg["status"] = true
                    }
                    return msg;
                })

                item["messages"] = messages;
            }
            return item;
        })
        result["friends"] = friends;
        setUserMainData(result);
        console.log("userdata", result, "data", data);
    }

    const handleActiveUser = () => {
        let result = { ...userMainData };
        let friends = result.friends.map((item) => {
            if (item.emailId == activeUserOption.emailId) {
                item.active=activeUserOption.active;
            }
            return item;
        })
        result["friends"] = friends;
        setUserMainData(result);
        console.log("hello this man is active", activeUserOption);
    }
    return (
        <UserContext.Provider value={{ user, loginUser, registerUser, logOutUser, sendMessage, userMainData, hanledCurretUser, addFriendToUser, currentUser, updateMessageStatus }}>
            {children}
        </UserContext.Provider>
    )
}
export default UserContextProvider;
