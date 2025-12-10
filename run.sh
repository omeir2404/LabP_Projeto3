#!/usr/bin/env zsh
set -euo pipefail

OUT=out
mkdir -p "$OUT"

echo "Compiling project (only the playable implementations + Filling enum)..."
javac -d "$OUT" Main.java Game.java Table.java Bottle.java src/types/Filling.java

echo "Compilation finished. To run the game, execute:"
echo "  java -cp $OUT types.Main"
echo "Or run this script with 'run' to start the game now:"
echo "  ./run.sh run"

if [ "${1:-}" = "run" ]; then
  java -cp "$OUT" types.Main
fi
