function stringToColor(string: string) {
  let hash = 0;
  let i;

  /* eslint-disable no-bitwise */
  for (i = 0; i < string.length; i += 1) {
    hash = string.charCodeAt(i) + ((hash << 5) - hash);
  }

  let color = "#";

  for (i = 0; i < 3; i += 1) {
    const value = (hash >> (i * 8)) & 0xff;
    color += `00${value.toString(16)}`.slice(-2);
  }
  /* eslint-enable no-bitwise */

  return color;
}

export const generateStringAvatar = (
  name: string,
  size: number | undefined
) => {
  const splitName = name.toLocaleUpperCase().split(" ");
  return {
    sx: {
      bgcolor: stringToColor(name),
      width: size ?? 34,
      height: size ?? 34,
      border: "1px solid white",
    },
    children: `${splitName[0][0]}${
      splitName.length > 1 ? splitName[1][0] : ""
    }`,
  };
};

export const getUserAvatar = (): string | null => {
  return JSON.parse(localStorage.getItem("userData") ?? "{}")["avatarUrl"];
};

export const getUserFullName = (): string => {
  return JSON.parse(localStorage.getItem("userData") ?? "{}")["fullName"] ?? "";
};

export const capitalizeStringParts = (value: string): string => {
  let newValue = "";

  for (let item of value.split(" ")) {
    if (!item) {
      continue;
    }

    newValue += item.toLocaleUpperCase()[0] + item.slice(1);
  }

  return newValue;
};

export const getUserUsername = (): string => {
  let name =
    JSON.parse(localStorage.getItem("userData") ?? "{}")["username"] ?? "";

  return capitalizeStringParts(name);
};
