"use client"

import React, {FC, useState} from "react";
import {Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, useDisclosure, Checkbox, Input, Link} from "@nextui-org/react";
import {LockIcon, MailIcon, TestIcon} from "@/components/icons";
import {Login} from "@/api/user";
interface LogInProps {
    onLoginSuccess: () => void;
}
export const LogIn: FC<LogInProps> = ({ onLoginSuccess }) => {
    const {isOpen, onOpen, onOpenChange} = useDisclosure();
    const [userEmail, setUserEmail] = useState('xiaosong.wen@gmail.com');
    const [password, setPassword] = useState('00000');

    const login = (onClose: () => void) =>{
        Login({userEmail:userEmail, password: password}).then((response) => {
            // Handle successful login (e.g., save token or user info)
            if (response.data.code == 200) {
                onClose();
                onLoginSuccess();
            } else {
                console.error('User failed');
            }
        })
    }

    return (
        <>
            <Button onPress={onOpen} color="primary" startContent={(<TestIcon />)}>Sign In</Button>
            <Modal
                isOpen={isOpen}
                onOpenChange={onOpenChange}
                placement="top-center"
            >
                <ModalContent>
                    {(onClose) => (
                        <>
                            <ModalHeader className="flex flex-col gap-1">Log in</ModalHeader>
                            <ModalBody>
                                <Input
                                    autoFocus
                                    endContent={
                                        <MailIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
                                    }
                                    label="Username"
                                    placeholder="Enter your username"
                                    value={userEmail}
                                    onChange={(e) => setUserEmail(e.target.value)}
                                    variant="bordered"
                                />
                                <Input
                                    endContent={
                                        <LockIcon className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
                                    }
                                    label="Password"
                                    placeholder="Enter your password"
                                    type="password"
                                    value={password}
                                    variant="bordered"
                                    onChange={(e) => setPassword(e.target.value)}
                                />
                                <div className="flex py-2 px-1 justify-between">
                                    <Checkbox
                                        classNames={{
                                            label: "text-small",
                                        }}
                                    >
                                        Remember me
                                    </Checkbox>
                                    <Link color="primary" href="#" size="sm">
                                        Forgot password?
                                    </Link>
                                </div>
                            </ModalBody>
                            <ModalFooter>
                                <Button color="danger" variant="flat" onPress={onClose}>
                                    Close
                                </Button>
                                <Button color="primary" onPress={() => login(onClose)}>
                                    Sign in
                                </Button>
                            </ModalFooter>
                        </>
                    )}
                </ModalContent>
            </Modal>
        </>
    );
}
