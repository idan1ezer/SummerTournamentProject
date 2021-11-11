# SummerTournamentProject
Tournament top 8 tree of some sports games - you can trace all records to the grand final!

Project planning and approach:
A tournament contain type of games and players, game contain phases.
Every player has 'score' vector that resets after each game.
On each game, the loser gets deleted from the 'players' vector (of same phase) and the winners (rest of the vector) advance to the next phase.

Basic assumptions for the project:
Knowledge of tennis rules, inserting correct scores on each set.
Do not add participants with the same name.
If there is a tied game between teams in basketball (after OT) and football (after OT & penalties), there is a coinflip.
You can start with any game you would like in the current phase (order isn't matter).
You must finish the games in current phase in order to procced to next one.
In each game you can see the winner in green box and the loser on red box.
You can't score more than 100 points in one basketball quarter, even MJ can't.
