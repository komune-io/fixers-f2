import { addons } from '@storybook/addons';
import {create} from "@storybook/theming";
import logo from "../public/logo.png";

addons.setConfig({
    theme: create({
        base: 'light',
        brandTitle: 'SmartB F2',
        brandUrl: 'https://docs.smartb.city/f2',
        brandImage: logo,
    }),
});
