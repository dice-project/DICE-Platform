This plugin contributes to the UI of DICE IDE with a generic Menu and a Preferences Page that all the partners should be used in order to adapt their tools correctly to DICE.

# Menu contribution
The menu ID is "org.dice.menu.tools". This ID should be used in all the contributions to the menu of the application using the "org.eclipse.ui.menus" extension point. Here is an example of contribution:
```
<extension point="org.eclipse.ui.menus">
    <menuContribution
        allPopups="false"
        locationURI="menu:org.dice.menu.tools">
        ...
    </menuContribution>
</extension>
```

You can see this full example of contribution in the "org.dice.monitoring" plugin.

# Preferences page contribution
The preferences page ID is "org.dice.ui.preferences". This ID should be used in all the contributions to the Preferences of the application using the "org.eclipse.ui.preferencesPages" extension point. Here is an example of contribution:

```
<extension point="org.eclipse.ui.preferencePages">
    <page
        category="org.dice.ui.preferences"
        class="org.dice.ui.preferences.pages.MonitoringPreferencesPage"
        id="org.dice.ui.preferences.monitoring"
        name="Monitoring Tools">
    </page>
</extension>
```

You can see this full example of contribution in the "org.dice.monitoring" plugin.
