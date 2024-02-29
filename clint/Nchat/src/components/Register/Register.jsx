import { useContext, useRef } from "react";
import styles from "../Login/Login.module.css"
import { UserContext } from "../../contextapi/UserContext";

const Register = () => {
    const { registerUser } = useContext(UserContext);
    const nameRef = useRef();
    const emailRef = useRef();
    const passwordRef = useRef();
    const bioRef = useRef();
    const imageRef = useRef();

    const submitData = () => {
        const formData = new FormData();
            formData.append("name", nameRef.current.value)
            formData.append("file",imageRef.current.files[0]);
            formData.append("emailId", emailRef.current.value);
            formData.append("bio",bioRef.current.value)
            formData.append("password",passwordRef.current.value)
           registerUser(formData);
            console.log(formData)
            console.log(imageRef.current.files[0])

            nameRef.current.value = ""
            emailRef.current.value = ""
            passwordRef.current.value = ""
            bioRef.current.value = "";
            imageRef.current = ""

    

    }
    return (

        <div className={styles.bodyContainer}>

            <div className={styles.centerCardContainer}>
                <div className={styles.marginTopBottom}>
                    <p >Enter Name</p>
                    <input ref={nameRef} className={styles.txtInput} type="text" name="name" id="name" />
                </div>
                <div className={styles.marginTopBottom}>
                    <p>Enter Email</p>
                    <input ref={emailRef} className={styles.txtInput} type="email" name="email" id="email" />
                </div>
                <div className={styles.marginTopBottom}>
                    <p>Enter Password</p>
                    <input ref={passwordRef} className={styles.txtInput} type="password" name="password" id="password" />

                </div>
                {<div className={styles.marginTopBottom}>
                    <p>Enter Bio</p>
                    <input ref={bioRef} className={styles.txtInput} type="text" name="text " id="text" />

                </div>}
                <div className={styles.marginTopBottom}>
                    <p>select Image</p>
                    <input ref={imageRef} className={styles.txtInput} type="file" name="file" id="file" />

                </div>
                <div >
                    <button className={styles.loginBtn} onClick={submitData}>register</button>
                </div>
                <div>
                    <p style={{ margin: "10px 0px", textAlign: "center" }}>Have Account <span style={{ color: "red" }}>Login</span></p>
                </div>
            </div>



        </div>
    )
}
export default Register;