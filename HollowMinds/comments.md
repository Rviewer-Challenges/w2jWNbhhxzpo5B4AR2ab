## App build process

The first decision that I took while making this application were the theme. I love **Hollow Knight** and the bugs from **Hallownest**, so I picked this theme.

I developed the app following the repository pattern and CLEAN and SOLID principles. 
 - The **datasource** class receives a number of pairs to display and process the output randomly from the possible characters.
 - The **repository** queries the data from the **datasource**. If in the future this data comes from any external API, the repository will be changed to invoke a new **datasource**.
 - One **usecase** retrieves the info from the **repository**. As the project is simple, there is only one use case: retrieve the info to display the game.
 - The configuration viewmodel (**ConfigurationViewModel**) pick a input from the user choosing the board size. The game viewmodel (**GameViewModel**) uses the previous mentioned **usecase** to load the data based on the board size and starts the game. 

I also included unit **testing** for those mentioned elements (**datasource**, **usecase** and **viewmodels**), as this guarantees that the main features that will invoke the views works.

