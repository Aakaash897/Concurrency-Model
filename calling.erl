-module(calling).
-export([handle/2]).

handle(Key,Val) ->
	receive
		{Id} ->
				%io:format("Calling ~w ~w ~w ~w ~n", [Id,self(),Key,Val]),
				intro_handle(Key,Val,Id),
				handle(Key,Val)
		after 1000 ->
				io:format("Process ~s has received no calls for 1 second,ending...~n",[Key])
				%exit(self(), ok)
		
	end.

intro_handle(Key,Val,Id) ->
	%timer:sleep(round(timer:seconds(random:uniform()))),
	lists:foreach( fun(X)->
		{MegaSeconds, Seconds, MicroSeconds}=now(),
		%io:format("~w ~n",[X]),
		Intmsg=io_lib:format("~w",[X]) ++ " received intro message from " ++ io_lib:format("~w",[Key]) ++ "[" ++ io_lib:format("~w",[MicroSeconds])++"]",
		%timer:sleep(round(timer:seconds(random:uniform()))),
		Id ! {Intmsg},
		reply_handle(Key,X,Id,MicroSeconds)
		%io:format("~s ~n",[Mainmsg])
		end, Val).
		
reply_handle(Key,X,Id,MicroSeconds) ->
	timer:sleep(100),
	Repmsg=io_lib:format("~w",[Key]) ++ " received reply message from " ++ io_lib:format("~w",[X]) ++ "[" ++ io_lib:format("~w",[MicroSeconds])++"]",
	%timer:sleep(round(timer:seconds(random:uniform()))),
	Id ! {Repmsg}.
