"use client"
import {
  Navbar as NextUINavbar,
  NavbarContent,
  NavbarMenu,
  NavbarMenuToggle,
  NavbarBrand,
  NavbarItem,
  NavbarMenuItem,
} from "@nextui-org/navbar";
import { Button } from "@nextui-org/button";
import { Kbd } from "@nextui-org/kbd";
import { Link } from "@nextui-org/link";
import { Input } from "@nextui-org/input";
import { link as linkStyles } from "@nextui-org/theme";
import NextLink from "next/link";
import clsx from "clsx";

import { siteConfig } from "@/config/site";
import { ThemeSwitch } from "@/components/theme-switch";
import {
    GithubIcon,
    SearchIcon,
    Logo, LogoutIcon,
} from "@/components/icons";
import {LogIn} from "@/components/LogIn";
import {useEffect, useState} from "react";
import {getUserInfo, LogOut} from "@/api/user";
import {Avatar, Popover, PopoverContent, PopoverTrigger} from "@nextui-org/react";
import {getInitials} from "@/utils/utils";

interface UserInfo {
    firstName: string,
    lastName: string,
    email: string,
    avatar: string,
}
export const Navbar = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [userInfo, setUserInfo] = useState<UserInfo | null>(null);

    const logout = async () => {
        try {
            await LogOut(); // Perform logout API call
            setIsLoggedIn(false); // Set logged out state
            setUserInfo(null); // Clear user info
        } catch (error) {
            console.error('Error logging out', error);
        }
    }
    const fetchUserInfo = () => {
        getUserInfo().then((response) => {
            if (response.data.code == 200) {
                setUserInfo(response.data.data);
                setIsLoggedIn(true);
            }else {
                setIsLoggedIn(false);
                setUserInfo(null);
            }
        });
    }
    useEffect(()=>{
        fetchUserInfo();
    }, []);

  const searchInput = (
    <Input
      aria-label="Search"
      classNames={{
        inputWrapper: "bg-default-100",
        input: "text-sm",
      }}
      endContent={
        <Kbd className="hidden lg:inline-block" keys={["command"]}>
          K
        </Kbd>
      }
      labelPlacement="outside"
      placeholder="Search..."
      startContent={
        <SearchIcon className="text-base text-default-400 pointer-events-none flex-shrink-0" />
      }
      type="search"
    />
  );

  return (
    <NextUINavbar maxWidth="xl" position="sticky">
      <NavbarContent className="basis-1/5 sm:basis-full" justify="start">
        <NavbarBrand as="li" className="gap-3 max-w-fit">
          <NextLink className="flex justify-start items-center gap-1" href="/">
            <Logo />
            <p className="font-bold text-inherit">Xiaosong</p>
          </NextLink>
        </NavbarBrand>
        <ul className="hidden lg:flex gap-4 justify-start ml-2">
          {siteConfig.navItems.map((item) => (
            <NavbarItem key={item.href}>
              <NextLink
                className={clsx(
                  linkStyles({ color: "foreground" }),
                  "data-[active=true]:text-primary data-[active=true]:font-medium",
                )}
                color="foreground"
                href={item.href}
              >
                {item.label}
              </NextLink>
            </NavbarItem>
          ))}
        </ul>
      </NavbarContent>

      <NavbarContent
        className="hidden sm:flex basis-1/5 sm:basis-full"
        justify="end"
      >
        <NavbarItem className="hidden sm:flex gap-2">
          <Link isExternal aria-label="Github" href={siteConfig.links.github}>
            <GithubIcon className="text-default-500" />
          </Link>
          <ThemeSwitch />
        </NavbarItem>
        <NavbarItem className="hidden lg:flex">{searchInput}</NavbarItem>
        <NavbarItem className="hidden md:flex">
            {isLoggedIn ? (
                <div className="flex items-center space-x-2">
                    <Popover placement="bottom" showArrow={true}>
                        <PopoverTrigger>
                            <Avatar showFallback name={getInitials(userInfo!.firstName, userInfo!.lastName)} src={userInfo?.avatar} />
                        </PopoverTrigger>
                        <PopoverContent>
                            <div className="px-1 py-2">
                                <div className="text-small font-bold">{userInfo!.email}</div>
                                <Button
                                    // color="warning"
                                    size="sm"
                                    variant="light"
                                    onPress={() => logout()} // Function to handle logout
                                >
                                    <LogoutIcon /> Logout
                                </Button>

                            </div>
                        </PopoverContent>
                    </Popover>

                </div>
            ) : (
                <LogIn onLoginSuccess={fetchUserInfo} />
            )}
        </NavbarItem>
      </NavbarContent>

      <NavbarContent className="sm:hidden basis-1 pl-4" justify="end">
        <Link isExternal aria-label="Github" href={siteConfig.links.github}>
          <GithubIcon className="text-default-500" />
        </Link>
        <ThemeSwitch />
        <NavbarMenuToggle />
      </NavbarContent>

      <NavbarMenu>
        {searchInput}
        <div className="mx-4 mt-2 flex flex-col gap-2">
          {siteConfig.navMenuItems.map((item, index) => (
            <NavbarMenuItem key={`${item}-${index}`}>
              <Link
                color={
                  index === 2
                    ? "primary"
                    : index === siteConfig.navMenuItems.length - 1
                      ? "danger"
                      : "foreground"
                }
                href="#"
                size="lg"
              >
                {item.label}
              </Link>
            </NavbarMenuItem>
          ))}
        </div>
      </NavbarMenu>
    </NextUINavbar>
  );
};
